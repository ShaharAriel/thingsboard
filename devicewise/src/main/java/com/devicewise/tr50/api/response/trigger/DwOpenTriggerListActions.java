package com.devicewise.tr50.api.response.trigger;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenTriggerListActions extends DwOpenGenericResponse{
	
	private ArrayList<DwOpenAction> result;
	
	public static class DwOpenAction{
		
		private ArrayList<ActionParam> inputs;
		private ArrayList<ActionParam> outputs;
		private String type;
		
		public static class ActionParam{
			
			private int count;
			private String key;
			private boolean multiline;
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
			public boolean isMultiline() {
				return multiline;
			}
			public void setMultiline(boolean multiline) {
				this.multiline = multiline;
			}
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public ArrayList<ActionParam> getInputs() {
			return inputs;
		}

		public void setInputs(ArrayList<ActionParam> inputs) {
			this.inputs = inputs;
		}

		public ArrayList<ActionParam> getOutputs() {
			return outputs;
		}

		public void setOutputs(ArrayList<ActionParam> outputs) {
			this.outputs = outputs;
		}
	}

	public ArrayList<DwOpenAction> getResult() {
		return result;
	}

	public void setResult(ArrayList<DwOpenAction> result) {
		this.result = result;
	}
}
