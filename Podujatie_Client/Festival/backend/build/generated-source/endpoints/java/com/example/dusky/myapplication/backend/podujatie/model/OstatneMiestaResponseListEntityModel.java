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
 * (build: 2016-05-19 20:48:09 UTC)
 * on 2016-05-30 at 15:35:47 UTC 
 * Modify at your own risk.
 */

package com.example.dusky.myapplication.backend.podujatie.model;

/**
 * Model definition for OstatneMiestaResponseListEntityModel.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the podujatie. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class OstatneMiestaResponseListEntityModel extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("count_batch") @com.google.api.client.json.JsonString
  private java.lang.Long countBatch;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<OstatneMiestaResponseEntityModel> ostatneMiestaList;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getCountBatch() {
    return countBatch;
  }

  /**
   * @param countBatch countBatch or {@code null} for none
   */
  public OstatneMiestaResponseListEntityModel setCountBatch(java.lang.Long countBatch) {
    this.countBatch = countBatch;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<OstatneMiestaResponseEntityModel> getOstatneMiestaList() {
    return ostatneMiestaList;
  }

  /**
   * @param ostatneMiestaList ostatneMiestaList or {@code null} for none
   */
  public OstatneMiestaResponseListEntityModel setOstatneMiestaList(java.util.List<OstatneMiestaResponseEntityModel> ostatneMiestaList) {
    this.ostatneMiestaList = ostatneMiestaList;
    return this;
  }

  @Override
  public OstatneMiestaResponseListEntityModel set(String fieldName, Object value) {
    return (OstatneMiestaResponseListEntityModel) super.set(fieldName, value);
  }

  @Override
  public OstatneMiestaResponseListEntityModel clone() {
    return (OstatneMiestaResponseListEntityModel) super.clone();
  }

}
