package com.devicewise.tr50.api.response.trigger;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenTriggerListEvents extends DwOpenGenericResponse{
	
	private ArrayList<DwOpenEvent> result;
	
	public static class DwOpenEvent{
		
		private ArrayList<EventParam> inputs;
		private ArrayList<EventParam> outputs;
		private String type;
		
		public static class EventParam{
			
			private int count;
			private String key;
			private String name;
			private boolean required;
			private String type;
			
			public String getKey() {
				return key;
			}
			public void setKey(String key) {
				this.key = key;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public boolean isRequired() {
				return required;
			}
			public void setRequired(boolean required) {
				this.required = required;
			}
			public String getType() {
				return type;
			}
			public void setType(String type) {
				this.type = type;
			}
			public int getCount() {
				return count;
			}
			public void setCount(int count) {
				this.count = count;
			}
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public ArrayList<EventParam> getInputs() {
			return inputs;
		}

		public void setInputs(ArrayList<EventParam> inputs) {
			this.inputs = inputs;
		}

		public ArrayList<EventParam> getOutputs() {
			return outputs;
		}

		public void setOutputs(ArrayList<EventParam> outputs) {
			this.outputs = outputs;
		}
	}

	public ArrayList<DwOpenEvent> getResult() {
		return result;
	}

	public void setResult(ArrayList<DwOpenEvent> result) {
		this.result = result;
	}
}
