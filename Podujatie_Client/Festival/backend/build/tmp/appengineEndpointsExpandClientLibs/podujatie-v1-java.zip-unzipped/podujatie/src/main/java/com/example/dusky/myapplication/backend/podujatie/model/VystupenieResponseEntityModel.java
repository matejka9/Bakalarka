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
 * Model definition for VystupenieResponseEntityModel.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the podujatie. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class VystupenieResponseEntityModel extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("cas_do")
  private com.google.api.client.util.DateTime casDo;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("cas_od")
  private com.google.api.client.util.DateTime casOd;

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
  @com.google.api.client.util.Key("podium_id") @com.google.api.client.json.JsonString
  private java.lang.Long podiumId;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("vystupenie_id") @com.google.api.client.json.JsonString
  private java.lang.Long vystupenieId;

  /**
   * @return value or {@code null} for none
   */
  public com.google.api.client.util.DateTime getCasDo() {
    return casDo;
  }

  /**
   * @param casDo casDo or {@code null} for none
   */
  public VystupenieResponseEntityModel setCasDo(com.google.api.client.util.DateTime casDo) {
    this.casDo = casDo;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public com.google.api.client.util.DateTime getCasOd() {
    return casOd;
  }

  /**
   * @param casOd casOd or {@code null} for none
   */
  public VystupenieResponseEntityModel setCasOd(com.google.api.client.util.DateTime casOd) {
    this.casOd = casOd;
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
  public VystupenieResponseEntityModel setDetail(java.lang.String detail) {
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
  public VystupenieResponseEntityModel setId(java.lang.Long id) {
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
  public VystupenieResponseEntityModel setNazov(java.lang.String nazov) {
    this.nazov = nazov;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getPodiumId() {
    return podiumId;
  }

  /**
   * @param podiumId podiumId or {@code null} for none
   */
  public VystupenieResponseEntityModel setPodiumId(java.lang.Long podiumId) {
    this.podiumId = podiumId;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getVystupenieId() {
    return vystupenieId;
  }

  /**
   * @param vystupenieId vystupenieId or {@code null} for none
   */
  public VystupenieResponseEntityModel setVystupenieId(java.lang.Long vystupenieId) {
    this.vystupenieId = vystupenieId;
    return this;
  }

  @Override
  public VystupenieResponseEntityModel set(String fieldName, Object value) {
    return (VystupenieResponseEntityModel) super.set(fieldName, value);
  }

  @Override
  public VystupenieResponseEntityModel clone() {
    return (VystupenieResponseEntityModel) super.clone();
  }

}
