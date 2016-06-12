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
 * Model definition for TovarResponseEntityModel.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the podujatie. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class TovarResponseEntityModel extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double cena;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String detail;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String nazov;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("stanok_id") @com.google.api.client.json.JsonString
  private java.lang.Long stanokId;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("tovar_id") @com.google.api.client.json.JsonString
  private java.lang.Long tovarId;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getCena() {
    return cena;
  }

  /**
   * @param cena cena or {@code null} for none
   */
  public TovarResponseEntityModel setCena(java.lang.Double cena) {
    this.cena = cena;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDetail() {
    return detail;
  }

  /**
   * @param detail detail or {@code null} for none
   */
  public TovarResponseEntityModel setDetail(java.lang.String detail) {
    this.detail = detail;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * @param id id or {@code null} for none
   */
  public TovarResponseEntityModel setId(java.lang.Long id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getNazov() {
    return nazov;
  }

  /**
   * @param nazov nazov or {@code null} for none
   */
  public TovarResponseEntityModel setNazov(java.lang.String nazov) {
    this.nazov = nazov;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getStanokId() {
    return stanokId;
  }

  /**
   * @param stanokId stanokId or {@code null} for none
   */
  public TovarResponseEntityModel setStanokId(java.lang.Long stanokId) {
    this.stanokId = stanokId;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getTovarId() {
    return tovarId;
  }

  /**
   * @param tovarId tovarId or {@code null} for none
   */
  public TovarResponseEntityModel setTovarId(java.lang.Long tovarId) {
    this.tovarId = tovarId;
    return this;
  }

  @Override
  public TovarResponseEntityModel set(String fieldName, Object value) {
    return (TovarResponseEntityModel) super.set(fieldName, value);
  }

  @Override
  public TovarResponseEntityModel clone() {
    return (TovarResponseEntityModel) super.clone();
  }

}
