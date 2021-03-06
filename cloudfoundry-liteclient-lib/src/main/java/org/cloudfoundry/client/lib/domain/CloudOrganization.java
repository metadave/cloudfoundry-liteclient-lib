/*
 * Copyright 2009-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cloudfoundry.client.lib.domain;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Thomas Risberg
 */
public class CloudOrganization extends CloudEntity {

	private boolean billingEnabled = false;

	public CloudOrganization(JSONObject meta, JSONObject entity) throws JSONException {
		this(new Meta(meta),
				entity.getString("name"),
				entity.getBoolean("billing_enabled"));
	}

	public CloudOrganization(Meta meta, String name) {
		this(meta, name, false);
	}

	public CloudOrganization(Meta meta, String name, boolean billingEnabled) {
		super(meta, name);
		this.billingEnabled = billingEnabled;
	}

	public boolean isBillingEnabled() {
		return billingEnabled;
	}
}
