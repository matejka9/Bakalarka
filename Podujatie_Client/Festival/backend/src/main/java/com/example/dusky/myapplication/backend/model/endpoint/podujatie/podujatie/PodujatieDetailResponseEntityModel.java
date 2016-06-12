package com.example.dusky.myapplication.backend.model.endpoint.podujatie.podujatie;

import com.example.dusky.myapplication.backend.model.endpoint.BaseEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.mapa.MapaResponseEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.ostatneMiesta.OstatneMiestaResponseListEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.podium.PodiumResponseListEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.stanok.StanokResponseListEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.tovar.TovarResponseListEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.vystupenie.VystupenieResponseListEntityModel;
import com.google.api.server.spi.config.ApiResourceProperty;

import java.sql.Blob;

/**
 * Created by dusky on 5/29/16.
 */
public class PodujatieDetailResponseEntityModel extends BaseEntityModel {

    @ApiResourceProperty(name = "mapa")
    protected MapaResponseEntityModel mapa;

    @ApiResourceProperty(name = "stanky")
    protected StanokResponseListEntityModel stanky;

    @ApiResourceProperty(name = "tovar")
    protected TovarResponseListEntityModel tovar;

    @ApiResourceProperty(name = "podia")
    protected PodiumResponseListEntityModel podia;

    @ApiResourceProperty(name = "vystupenia")
    protected VystupenieResponseListEntityModel vystupenia;

    @ApiResourceProperty(name = "ostatne_miesta")
    protected OstatneMiestaResponseListEntityModel ostatneMiesta;

    public PodujatieDetailResponseEntityModel(Long id){
        super.id = id;
    }

    public MapaResponseEntityModel getMapa() {
        return mapa;
    }

    public void setMapa(MapaResponseEntityModel mapa) {
        this.mapa = mapa;
    }

    public StanokResponseListEntityModel getStanky() {
        return stanky;
    }

    public void setStanky(StanokResponseListEntityModel stanky) {
        this.stanky = stanky;
    }

    public TovarResponseListEntityModel getTovar() {
        return tovar;
    }

    public void setTovar(TovarResponseListEntityModel tovar) {
        this.tovar = tovar;
    }

    public PodiumResponseListEntityModel getPodia() {
        return podia;
    }

    public void setPodia(PodiumResponseListEntityModel podia) {
        this.podia = podia;
    }

    public VystupenieResponseListEntityModel getVystupenia() {
        return vystupenia;
    }

    public void setVystupenia(VystupenieResponseListEntityModel vystupenia) {
        this.vystupenia = vystupenia;
    }

    public OstatneMiestaResponseListEntityModel getOstatneMiesta() {
        return ostatneMiesta;
    }

    public void setOstatneMiesta(OstatneMiestaResponseListEntityModel ostatneMiesta) {
        this.ostatneMiesta = ostatneMiesta;
    }

    @Override
    public String toString() {
        return "PodujatieDetailResponseEntityModel{" +
                "mapa=" + mapa +
                ", stanky=" + stanky +
                ", tovar=" + tovar +
                ", podia=" + podia +
                ", vystupenia=" + vystupenia +
                ", ostatneMiesta=" + ostatneMiesta +
                '}';
    }
}
