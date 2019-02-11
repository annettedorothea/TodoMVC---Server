/* 
 * Copyright (c) 2019, Annette Pohl, Koblenz, Germany
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.

 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */




package com.anfelisa.ace;

public class AceOperation {

	private ITimelineItem action;
	private ITimelineItem command;
	private ITimelineItem event;
	private String uuid;

	public AceOperation(String uuid) {
		super();
		this.uuid = uuid;
	}
	public ITimelineItem getAction() {
		return action;
	}
	public ITimelineItem getCommand() {
		return command;
	}
	public ITimelineItem getEvent() {
		return event;
	}
	public void setAction(ITimelineItem action) {
		this.action = action;
	}
	public void setCommand(ITimelineItem command) {
		this.command = command;
	}
	public void setEvent(ITimelineItem event) {
		this.event = event;
	}
	public String getUuid() {
		return uuid;
	}
	
}




/******* S.D.G. *******/



