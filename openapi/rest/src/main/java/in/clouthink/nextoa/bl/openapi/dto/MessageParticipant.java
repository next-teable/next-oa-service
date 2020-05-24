package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.model.PaperAction;
import in.clouthink.nextoa.bl.model.Receiver;
import in.clouthink.nextoa.bl.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 */
public class MessageParticipant {

	public static MessageParticipant from(User fromUser, User toUser, PaperAction action) {
		if (action == null) {
			return null;
		}
		MessageParticipant result = new MessageParticipant();
		List<User> to = new ArrayList<>();
		to.add(fromUser);
		to.addAll(action.getToReceivers()
						.stream()
						.filter(receiver -> skipSender(fromUser, receiver))
						.map(receiver -> receiver.getUser())
						.filter(user -> !user.getId().equalsIgnoreCase(toUser.getId()))
						.collect(Collectors.toList()));

		result.setTo(ReceiverSummary.fromUsers(to));

		List<User> cc = new ArrayList<>();
		cc.addAll(action.getCcReceivers()
						.stream()
						.filter(receiver -> skipSender(fromUser, receiver))
						.map(receiver -> receiver.getUser())
						.filter(user -> !user.getId().equalsIgnoreCase(toUser.getId()))
						.collect(Collectors.toList()));

		result.setCc(ReceiverSummary.fromUsers(cc));
		return result;
	}

	private static boolean skipSender(User sender, Receiver receiver) {
		if (receiver.getUser() == null) {
			return false;
		}
		if (receiver.getUser().getId().equalsIgnoreCase(sender.getId())) {
			return false;
		}
		return true;
	}

	private List<ReceiverSummary> to;

	private List<ReceiverSummary> cc;

	public List<ReceiverSummary> getTo() {
		return to;
	}

	public void setTo(List<ReceiverSummary> to) {
		this.to = to;
	}

	public List<ReceiverSummary> getCc() {
		return cc;
	}

	public void setCc(List<ReceiverSummary> cc) {
		this.cc = cc;
	}
}
