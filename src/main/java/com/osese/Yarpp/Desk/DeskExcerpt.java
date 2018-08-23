package com.osese.Yarpp.Desk;

import org.springframework.data.rest.core.config.Projection;

import com.osese.Yarpp.DeskPosition.DeskPosition;
import com.osese.Yarpp.RestoUser.RestoUser;

@Projection(name="deskposExcerpt", types=Desk.class)
public interface DeskExcerpt {
	Integer getId();
	String getName();
	Boolean getState();
	DeskPosition getPosition();
	RestoUser getUser();
}
