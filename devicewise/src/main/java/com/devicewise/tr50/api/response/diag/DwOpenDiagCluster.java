package com.devicewise.tr50.api.response.diag;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenDiagCluster extends DwOpenGenericResponse{
	
	private ArrayList<Members> members;
	
	public static class Members{
		
		private String serverid;
		private String state;
		private boolean isMaster;
		private int cpu;
		private int sysmem;
		private int procmem;
		private int goroutines;
		private int connsMqtt;
		private String version;
		private String built;
		private LinkedHashMap<String,Network> network;
		
		public String getServerid() {
			return serverid;
		}
		public void setServerid(String serverid) {
			this.serverid = serverid;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public boolean getisMaster() {
			return isMaster;
		}
		public void setisMaster(boolean isMaster) {
			this.isMaster = isMaster;
		}
		public int getCpu() {
			return cpu;
		}
		public void setCpu(int cpu) {
			this.cpu = cpu;
		}
		public int getSysmem() {
			return sysmem;
		}
		public void setSysmem(int sysmem) {
			this.sysmem = sysmem;
		}
		public int getProcmem() {
			return procmem;
		}
		public void setProcmem(int procmem) {
			this.procmem = procmem;
		}
		public int getGoroutines() {
			return goroutines;
		}
		public void setGoroutines(int goroutines) {
			this.goroutines = goroutines;
		}
		public int getConnsMqtt() {
			return connsMqtt;
		}
		public void setConnsMqtt(int connsMqtt) {
			this.connsMqtt = connsMqtt;
		}
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		public String getBuilt() {
			return built;
		}
		public void setBuilt(String built) {
			this.built = built;
		}
		
		public LinkedHashMap<String,Network> getNetwork() {
			return network;
		}
		public void setNetwork(LinkedHashMap<String,Network> network) {
			this.network = network;
		}

		public static class Network{
			
			private double mbpsIn;
			private double mbpsOut;
			private double ppsIn;
			private double ppsOut;
			
			public double getMbpsIn() {
				return mbpsIn;
			}
			public void setMbpsIn(double mbpsIn) {
				this.mbpsIn = mbpsIn;
			}
			public double getMbpsOut() {
				return mbpsOut;
			}
			public void setMbpsOut(double mbpsOut) {
				this.mbpsOut = mbpsOut;
			}
			public double getPpsIn() {
				return ppsIn;
			}
			public void setPpsIn(double ppsIn) {
				this.ppsIn = ppsIn;
			}
			public double getPpsOut() {
				return ppsOut;
			}
			public void setPpsOut(double ppsOut) {
				this.ppsOut = ppsOut;
			}
			
		}
	}

	public ArrayList<Members> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<Members> members) {
		this.members = members;
	}
}
