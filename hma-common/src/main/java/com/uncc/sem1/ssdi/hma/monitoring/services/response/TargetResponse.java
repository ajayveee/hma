package com.uncc.sem1.ssdi.hma.monitoring.services.response;

import java.util.ArrayList;
import java.util.List;

import com.uncc.sem1.ssdi.hma.monitoring.domain.Target;

public class TargetResponse extends HMAResponse {
	private List<Target> targets;

	public TargetResponse() {
		super();
		targets = new ArrayList<Target>();
	}

	public List<Target> getTargets() {
		return targets;
	}

	public void setTargets(List<Target> targets) {
		this.targets = targets;
	}
	
}
