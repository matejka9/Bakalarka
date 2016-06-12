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
 * Model definition for PodujatieDetailResponseEntityModel.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the podujatie. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class PodujatieDetailResponseEntityModel extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private MapaResponseEntityModel mapa;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("ostatne_miesta")
  private OstatneMiestaResponseListEntityModel ostatneMiesta;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private PodiumResponseListEntityModel podia;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private StanokResponseListEntityModel stanky;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private TovarResponseListEntityModel tovar;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private VystupenieResponseListEntityModel vystupenia;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * @param id id or {@code null} for none
   */
  public PodujatieDetailResponseEntityModel setId(java.lang.Long id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public MapaResponseEntityModel getMapa() {
    return mapa;
  }

  /**
   * @param mapa mapa or {@code null} for none
   */
  public PodujatieDetailResponseEntityModel setMapa(MapaResponseEntityModel mapa) {
    this.mapa = mapa;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public OstatneMiestaResponseListEntityModel getOstatneMiesta() {
    return ostatneMiesta;
  }

  /**
   * @param ostatneMiesta ostatneMiesta or {@code null} for none
   */
  public PodujatieDetailResponseEntityModel setOstatneMiesta(OstatneMiestaResponseListEntityModel ostatneMiesta) {
    this.ostatneMiesta = ostatneMiesta;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public PodiumResponseListEntityModel getPodia() {
    return podia;
  }

  /**
   * @param podia podia or {@code null} for none
   */
  public PodujatieDetailResponseEntityModel setPodia(PodiumResponseListEntityModel podia) {
    this.podia = podia;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public StanokResponseListEntityModel getStanky() {
    return stanky;
  }

  /**
   * @param stanky stanky or {@code null} for none
   */
  public PodujatieDetailResponseEntityModel setStanky(StanokResponseListEntityModel stanky) {
    this.stanky = stanky;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public TovarResponseListEntityModel getTovar() {
    return tovar;
  }

  /**
   * @param tovar tovar or {@code null} for none
   */
  public PodujatieDetailResponseEntityModel setTovar(TovarResponseListEntityModel tovar) {
    this.tovar = tovar;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public VystupenieResponseListEntityModel getVystupenia() {
    return vystupenia;
  }

  /**
   * @param vystupenia vystupenia or {@code null} for none
   */
  public PodujatieDetailResponseEntityModel setVystupenia(VystupenieResponseListEntityModel vystupenia) {
    this.vystupenia = vystupenia;
    return this;
  }

  @Override
  public PodujatieDetailResponseEntityModel set(String fieldName, Object value) {
    return (PodujatieDetailResponseEntityModel) super.set(fieldName, value);
  }

  @Override
  public PodujatieDetailResponseEntityModel clone() {
    return (PodujatieDetailResponseEntityModel) super.clone();
  }

}