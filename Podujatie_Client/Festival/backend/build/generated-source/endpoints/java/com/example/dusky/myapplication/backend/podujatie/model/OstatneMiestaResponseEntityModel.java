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
 * Model definition for OstatneMiestaResponseEntityModel.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the podujatie. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class OstatneMiestaResponseEntityModel extends com.google.api.client.json.GenericJson {

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
  private java.lang.Double latidute;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double longitude;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("mapa_id") @com.google.api.client.json.JsonString
  private java.lang.Long mapaId;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String nazov;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDetail() {
    return detail;
  }

  /**
   * @param detail detail or {@code null} for none
   */
  public OstatneMiestaResponseEntityModel setDetail(java.lang.String detail) {
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
  public OstatneMiestaResponseEntityModel setId(java.lang.Long id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getLatidute() {
    return latidute;
  }

  /**
   * @param latidute latidute or {@code null} for none
   */
  public OstatneMiestaResponseEntityModel setLatidute(java.lang.Double latidute) {
    this.latidute = latidute;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getLongitude() {
    return longitude;
  }

  /**
   * @param longitude longitude or {@code null} for none
   */
  public OstatneMiestaResponseEntityModel setLongitude(java.lang.Double longitude) {
    this.longitude = longitude;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getMapaId() {
    return mapaId;
  }

  /**
   * @param mapaId mapaId or {@code null} for none
   */
  public OstatneMiestaResponseEntityModel setMapaId(java.lang.Long mapaId) {
    this.mapaId = mapaId;
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
  public OstatneMiestaResponseEntityModel setNazov(java.lang.String nazov) {
    this.nazov = nazov;
    return this;
  }

  @Override
  public OstatneMiestaResponseEntityModel set(String fieldName, Object value) {
    return (OstatneMiestaResponseEntityModel) super.set(fieldName, value);
  }

  @Override
  public OstatneMiestaResponseEntityModel clone() {
    return (OstatneMiestaResponseEntityModel) super.clone();
  }

}
