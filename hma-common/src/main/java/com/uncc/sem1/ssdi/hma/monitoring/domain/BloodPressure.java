package com.uncc.sem1.ssdi.hma.monitoring.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum BloodPressure {
	NORMAL, PREHYPERTENSION, HIGHBLOODPRESSURESTAGE1, HIGHBLOODPRESSURESTAGE2, HYPERTENSIVECRISIS
}
