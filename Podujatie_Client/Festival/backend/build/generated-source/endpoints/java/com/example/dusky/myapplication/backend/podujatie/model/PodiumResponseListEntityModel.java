/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://github.com/google/apis-client-generator/
 * (build: 2016-05-27 16:00:31 UTC)
 * on 2016-06-12 at 14:30:13 UTC 
 * Modify at your own risk.
 */

package com.example.dusky.myapplication.backend.podujatie.model;

/**
 * Model definition for PodiumResponseListEntityModel.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the podujatie. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class PodiumResponseListEntityModel extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("count_batch") @com.google.api.client.json.JsonString
  private java.lang.Long countBatch;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<PodiumResponseEntityModel> podiaList;

  static {
    // hack to force ProGuard to consider PodiumResponseEntityModel used, since otherwise it would be stripped out
    // see https://github.com/google/google-api-java-client/issues/543
    com.google.api.client.util.Data.nullOf(PodiumResponseEntityModel.class);
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getCountBatch() {
    return countBatch;
  }

  /**
   * @param countBatch countBatch or {@code null} for none
   */
  public PodiumResponseListEntityModel setCountBatch(java.lang.Long countBatch) {
    this.countBatch = countBatch;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<PodiumResponseEntityModel> getPodiaList() {
    return podiaList;
  }

  /**
   * @param podiaList podiaList or {@code null} for none
   */
  public PodiumResponseListEntityModel setPodiaList(java.util.List<PodiumResponseEntityModel> podiaList) {
    this.podiaList = podiaList;
    return this;
  }

  @Override
  public PodiumResponseListEntityModel set(String fieldName, Object value) {
    return (PodiumResponseListEntityModel) super.set(fieldName, value);
  }

  @Override
  public PodiumResponseListEntityModel clone() {
    return (PodiumResponseListEntityModel) super.clone();
  }

}
