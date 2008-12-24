/**
* BigBlueButton open source conferencing system - http://www.bigbluebutton.org/
*
* Copyright (c) 2008 by respective authors (see below).
*
* This program is free software; you can redistribute it and/or modify it under the
* terms of the GNU Lesser General Public License as published by the Free Software
* Foundation; either version 2.1 of the License, or (at your option) any later
* version.
*
* This program is distributed in the hope that it will be useful, but WITHOUT ANY
* WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
* PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License along
* with this program; if not, write to the Free Software Foundation, Inc.,
* 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
* 
*/
package org.bigbluebuttonproject.asterisk.meetme;

import java.beans.PropertyChangeListener;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.asteriskjava.live.ManagerCommunicationException;
import org.asteriskjava.live.MeetMeUser;
import org.bigbluebuttonproject.asterisk.IConference;
import org.bigbluebuttonproject.asterisk.IParticipant;


/**
 * The Class MeetMeUserAdapter.
 */
public class MeetMeUserAdapter implements IParticipant {
	
	/** The log. */
	protected static Logger log = LoggerFactory.getLogger(MeetMeUserAdapter.class);
	
	/** The user. */
	private MeetMeUser user;
	
	/** The room number. */
	private String roomNumber;
	
	/** The caller id name. */
	private String callerIdName;
	
	/** The caller id number. */
	private String callerIdNumber;
	
	/** The date joined. */
	private Date dateJoined;
	
	/** The date left. */
	private Date dateLeft;
	
	/** The user number. */
	private Integer userNumber;
	
	/** The muted. */
	private Boolean muted;
	
	/** The talking. */
	private Boolean talking;
	
	/**
	 * Instantiates a new meet me user adapter.
	 * 
	 * @param user the user
	 */
	public MeetMeUserAdapter(MeetMeUser user) {
		this.user = user;
	}
	
	/**
	 * @see org.bigbluebuttonproject.asterisk.IParticipant#getCallerIdName()
	 */
	public String getCallerIdName() {
		return user.getChannel().getCallerId().getName();
	}

	/**
	 * @see org.bigbluebuttonproject.asterisk.IParticipant#getCallerIdNumber()
	 */
	public String getCallerIdNumber() {
		return user.getChannel().getCallerId().getNumber();
	}

	/**
	 * @see org.bigbluebuttonproject.asterisk.IParticipant#getConference()
	 */
	public IConference getConference() {
		return new MeetMeRoomAdapter(user.getRoom());
	}

	/**
	 * @see org.bigbluebuttonproject.asterisk.IParticipant#getDateJoined()
	 */
	public Date getDateJoined() {
		return user.getDateJoined();
	}

	/**
	 * @see org.bigbluebuttonproject.asterisk.IParticipant#getDateLeft()
	 */
	public Date getDateLeft() {
		return user.getDateLeft();
	}

	/**
	 * @see org.bigbluebuttonproject.asterisk.IParticipant#getParticipantId()
	 */
	public Integer getParticipantId() {
		return user.getUserNumber();
	}
	
	/**
	 * @see org.bigbluebuttonproject.asterisk.IParticipant#isMuted()
	 */
	public boolean isMuted() {
		return user.isMuted();
	}

	/**
	 * @see org.bigbluebuttonproject.asterisk.IParticipant#isTalking()
	 */
	public boolean isTalking() {
		return user.isTalking();
	}

	/**
	 * @see org.bigbluebuttonproject.asterisk.IParticipant#kick()
	 */
	public void kick() {
		try {
			user.kick();
		} catch (ManagerCommunicationException e) {
			log.error("Failed to kick participant: " + user.getUserNumber() + " due to '" + e.getMessage() + "'");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see org.bigbluebuttonproject.asterisk.IParticipant#mute()
	 */
	public void mute() {
		try {
			user.mute();
		} catch (ManagerCommunicationException e) {
			log.error("Failed to mute participant: " + user.getUserNumber() + " due to '" + e.getMessage() + "'");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see org.bigbluebuttonproject.asterisk.IParticipant#unmute()
	 */
	public void unmute() {
		try {
			user.unmute();
		} catch (ManagerCommunicationException e) {
			log.error("Failed to unmute participant: " + user.getUserNumber() + " due to '" + e.getMessage() + "'");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see org.bigbluebuttonproject.asterisk.IParticipant#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		user.addPropertyChangeListener(listener);
	}
}