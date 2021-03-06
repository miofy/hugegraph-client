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

package com.baidu.hugegraph.api;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.baidu.hugegraph.api.gremlin.GremlinRequest;
import com.baidu.hugegraph.structure.gremlin.ResultSet;
import com.baidu.hugegraph.testutil.Assert;

public class GremlinApiTest extends BaseApiTest {

    @BeforeClass
    public static void prepareSchema() {
        BaseApiTest.initPropertyKey();
        BaseApiTest.initVertexLabel();
        BaseApiTest.initEdgeLabel();
    }

    @Before
    public void prepareData() {
        BaseApiTest.initVertex();
        BaseApiTest.initEdge();
    }

    @Test
    public void testQueryAllVertices() {
        GremlinRequest request = new GremlinRequest("g.V()");
        ResultSet resultSet = gremlin().execute(request);

        Assert.assertEquals(6, resultSet.size());

        request = new GremlinRequest("g.V().drop()");
        gremlin().execute(request);

        request = new GremlinRequest("g.V()");
        resultSet = gremlin().execute(request);

        Assert.assertEquals(0, resultSet.size());
    }

    @Test
    public void testQueryAllEdges() {
        GremlinRequest request = new GremlinRequest("g.E()");
        ResultSet resultSet = gremlin().execute(request);

        Assert.assertEquals(6, resultSet.size());

        request = new GremlinRequest("g.E().drop()");
        gremlin().execute(request);

        request = new GremlinRequest("g.E()");
        resultSet = gremlin().execute(request);

        Assert.assertEquals(0, resultSet.size());
    }

    @Test
    public void testAsyncRemoveAllVertices() {
        GremlinRequest request = new GremlinRequest("g.V()");
        ResultSet resultSet = gremlin().execute(request);
        Assert.assertEquals(6, resultSet.size());

        String gremlin = "hugegraph.traversal().V().drop()";
        request = new GremlinRequest(gremlin);
        long id = gremlin().executeAsTask(request);
        waitUntilTaskCompleted(id);

        request = new GremlinRequest("g.V()");
        resultSet = gremlin().execute(request);
        Assert.assertEquals(0, resultSet.size());
    }

    @Test
    public void testAsyncRemoveAllEdges() {

        GremlinRequest request = new GremlinRequest("g.E()");
        ResultSet resultSet = gremlin().execute(request);
        Assert.assertEquals(6, resultSet.size());

        String gremlin = "g.E().drop()";
        request = new GremlinRequest(gremlin);
        long id = gremlin().executeAsTask(request);
        waitUntilTaskCompleted(id);

        request = new GremlinRequest("g.E()");
        resultSet = gremlin().execute(request);
        Assert.assertEquals(0, resultSet.size());
    }
}
