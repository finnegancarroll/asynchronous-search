/*
 *   Copyright 2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License").
 *   You may not use this file except in compliance with the License.
 *   A copy of the License is located at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   or in the "license" file accompanying this file. This file is distributed
 *   on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 *   express or implied. See the License for the specific language governing
 *   permissions and limitations under the License.
 */

package com.amazon.opendistroforelasticsearch.search.async.request;

import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.common.Nullable;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.unit.TimeValue;

import java.io.IOException;

/**
 * A request to fetch an async search response by id.
 */
public class GetAsyncSearchRequest extends AsyncSearchRoutingRequest<GetAsyncSearchRequest> {

    public static final TimeValue DEFAULT_WAIT_FOR_COMPLETION_TIMEOUT = TimeValue.timeValueSeconds(1);

    @Nullable
    private TimeValue waitForCompletionTimeout = DEFAULT_WAIT_FOR_COMPLETION_TIMEOUT;
    @Nullable
    private TimeValue keepAlive;

    public GetAsyncSearchRequest(String id) {
        super(id);
    }

    public TimeValue getWaitForCompletionTimeout() {
        return waitForCompletionTimeout;
    }

    public void setWaitForCompletionTimeout(TimeValue waitForCompletionTimeout) {
        this.waitForCompletionTimeout = waitForCompletionTimeout;
    }

    public TimeValue getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(TimeValue keepAlive) {
        this.keepAlive = keepAlive;
    }


    public GetAsyncSearchRequest(StreamInput streamInput) throws IOException {
        super(streamInput);
        keepAlive = streamInput.readOptionalTimeValue();
        waitForCompletionTimeout = streamInput.readOptionalTimeValue();
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeOptionalTimeValue(keepAlive);
        out.writeOptionalTimeValue(waitForCompletionTimeout);
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }
}
