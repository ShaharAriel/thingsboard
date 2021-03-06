/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 ILS Technology, LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.devicewise.tr50.examples.methods;

import java.util.LinkedHashMap;

import com.devicewise.tr50.api.events.DwOpenMethodCallback;
import com.devicewise.tr50.helpers.StringReply;

public class MethodReturnOutParams extends DwOpenMethodCallback{

	public MethodReturnOutParams(String methodKey) {
		super(methodKey);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int executeMethod(String execId,
			LinkedHashMap<String, Object> inParams,
			LinkedHashMap<String, Object> outParams, StringReply errorMessage) {
		
		System.out.println("Current Mailbox ID:"+getCurrentMailboxMessage().getId());
		System.out.println("Current ThingKey:"+getCurrentMailboxMessage().getThingKey());
		System.out.println("Current From:"+getCurrentMailboxMessage().getFrom());
		
		outParams.put("output_bool", true);
		outParams.put("output_float_array", new float[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25});
		outParams.put("output_integer",-9999);
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onMethodAckFailure(int errorCode, String errorMessage, Throwable error) {
		// TODO Auto-generated method stub
		
	}

}
