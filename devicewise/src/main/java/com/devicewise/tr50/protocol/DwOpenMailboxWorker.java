package com.devicewise.tr50.protocol;

import java.io.IOException;

import com.devicewise.tr50.api.response.mailbox.DwOpenMailboxCheck;
import com.devicewise.tr50.api.response.mailbox.DwOpenMailboxMessage;
import com.devicewise.tr50.exception.DwOpenException;


public class DwOpenMailboxWorker implements Runnable{

	private DwClient client;
	public DwOpenMailboxWorker(DwClient client){
		this.client = client;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		if(client!=null){
			DwOpenMailboxCheck checkResponse  = new DwOpenMailboxCheck();

			int ret=-1;
//			do {

				try {
					ret = client.getWorker().Mailbox().check(false, null, checkResponse);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					client.onMailboxReceive(-1,e, null);
					e.printStackTrace();
					return;
				} catch (DwOpenException e) {
					// TODO Auto-generated catch block
					client.onMailboxReceive(e.getErrorCode(),e, null);
					e.printStackTrace();
					return;
				}

				if(ret!=0)
					client.onMailboxReceive(ret,null,null);

				else if(checkResponse!=null){

					if(checkResponse.isSuccess()){
						
						if(checkResponse.getMessages()!=null){
							for(DwOpenMailboxMessage message:checkResponse.getMessages()){
								client.onMailboxReceive(0,null,message);
							}
						}
					}

					else
						client.onMailboxReceive(checkResponse.getErrorcodes()[0],new DwOpenException(checkResponse.getErrorcodes()[0],checkResponse.getErrormessages()[0]),null);
				}
//			}
//			while(ret==0 && checkResponse.isSuccess() && checkResponse.getMessages()!=null && !checkResponse.getMessages().isEmpty());

		}
	}

}
