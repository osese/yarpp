package com.osese.Yarpp.DeskPosition;

import java.util.Collection;

import org.springframework.data.rest.core.config.Projection;

import com.osese.Yarpp.Desk.Desk;


@Projection(name="deskpositionExcerpt", types=DeskPosition.class)
public interface DeskPositionExcerpt {
	String getName();
	Collection<Desk> getTables();
}
