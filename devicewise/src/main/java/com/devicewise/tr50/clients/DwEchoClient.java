package com.devicewise.tr50.clients;

import java.util.concurrent.ExecutorService;

import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwQueuedPacket;

public class DwEchoClient extends DwClient{

	private ExecutorService threadPool = java.util.concurrent.Executors.newFixedThreadPool(5);

	public DwEchoClient(){
		super();
	}

	@Override
	public int connect() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int disconnect() {
		// TODO Auto-generated method stub
		threadPool.shutdown();
		return 0;
	}

	@Override
	public int sendJSONRequest(int msgID, String json, StringReply reply)
			throws DwOpenException {
		reply.setValue(json);
		return 0;
	}

	@Override
	public int sendAsyncJSONRequest(int msgId, String json) throws DwOpenException {
		// TODO Auto-generated method stub
		synchronized(queue){
			if(queue.containsKey(msgId)){
				DwQueuedPacket qpkt = queue.get(msgId);
				removeQueue(msgId);
				if(qpkt.getResponse() instanceof StringReply)
					((StringReply)qpkt.getResponse()).setValue(json);
				DwQueuedPacketCallBack callback = new DwQueuedPacketCallBack(this,qpkt,null,0);
				threadPool.submit(callback);
			}
		}
		return 0;
	}

	private class DwQueuedPacketCallBack implements Runnable {
		DwQueuedPacket qpkt = null;
		DwClient source = null;
		int retCode;
		Throwable cause;

		//DoCallBack()
		public void run() {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(qpkt!=null)
				qpkt.getRecv().onReceive(source, retCode, cause, qpkt.getPacket(), qpkt.getResponse());
			else
				source.onReceive(-1, cause, null, null);
		}

		public DwQueuedPacketCallBack(DwClient _source, DwQueuedPacket _packet, Throwable _cause, int _ret) {
			source = _source;
			qpkt = _packet;
			retCode = _ret;
			cause = _cause;
		}
	}

}
