/*
 * Copyright 2017 HugeGraph Authors
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.baidu.hugegraph.structure;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.baidu.hugegraph.util.E;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableSet;

public class Task {

    @JsonProperty
    private long id;

    @JsonProperty(P.TYPE)
    private String type;

    @JsonProperty(P.NAME)
    private String name;

    @JsonProperty(P.STATUS)
    private String status;

    @JsonProperty(P.CALLABLE)
    private String callable;

    @JsonProperty(P.CREATE)
    private long create;

    @JsonProperty(P.UPDATE)
    private long update;

    @JsonProperty(P.PROGRESS)
    private long progress;

    @JsonProperty(P.RETRIES)
    private long retries;

    @JsonProperty(P.INPUT)
    private String input;

    @JsonProperty(P.RESULT)
    private Object result;

    @JsonProperty(P.DESCRIPTION)
    private String description;

    @JsonProperty(P.DEPENDENCIES)
    private Set<Long> dependencies;

    public long id() {
        return this.id;
    }

    public String type() {
        return this.type;
    }

    public String name() {
        return name;
    }

    public String status() {
        return status;
    }

    public String callable() {
        return callable;
    }

    public long createTime() {
        return create;
    }

    public long updateTime() {
        return update;
    }

    public long progress() {
        return progress;
    }

    public long retries() {
        return retries;
    }

    public String input() {
        return input;
    }

    public Object result() {
        return result;
    }

    public String description() {
        return description;
    }

    public Set<Long> dependencies() {
        return this.dependencies;
    }

    public boolean completed() {
        return ImmutableSet.of("success", "failed", "cancelled")
                           .contains(this.status);
    }

    public boolean success() {
        return "success".equals(this.status);
    }

    public Map<String, Object> asMap() {
        E.checkState(this.name != null, "Task name can't be null");

        Map<String, Object> map = new HashMap<>();

        map.put(P.ID, this.id);
        map.put(P.TYPE, this.type);
        map.put(P.NAME, this.name);
        map.put(P.CALLABLE, this.callable);
        map.put(P.STATUS, this.status);
        map.put(P.PROGRESS, this.progress);
        map.put(P.CREATE, this.create);
        map.put(P.RETRIES, this.retries);
        if (this.description != null) {
            map.put(P.DESCRIPTION, this.description);
        }
        if (this.update != 0) {
            map.put(P.UPDATE, this.update);
        }
        if (this.input != null) {
            map.put(P.INPUT, this.input);
        }
        if (this.result != null) {
            map.put(P.RESULT, this.result);
        }
        if (this.dependencies != null) {
            map.put(P.DEPENDENCIES, this.dependencies);
        }

        return map;
    }

    public static final class P {

        public static final String ID = "id";
        public static final String TYPE = "task_type";
        public static final String NAME = "task_name";
        public static final String CALLABLE = "task_callable";
        public static final String DESCRIPTION = "task_description";
        public static final String STATUS = "task_status";
        public static final String PROGRESS = "task_progress";
        public static final String CREATE = "task_create";
        public static final String UPDATE = "task_update";
        public static final String RETRIES = "task_retries";
        public static final String INPUT = "task_input";
        public static final String RESULT = "task_result";
        public static final String DEPENDENCIES = "task_dependencies";
    }
}
