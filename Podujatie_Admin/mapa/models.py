from __future__ import unicode_literals


from django.db import models
from Podujatie.models import Podujatie

# Create your models here.


class Mapa(models.Model):
    podujatie = models.ForeignKey(Podujatie, null=False, blank = False, default = None)
    mapka = models.TextField(null=False, blank = False, default = None)
    lavy_horny_roh_gps_latitude = models.FloatField(null=False, blank = False, default = None)
    lavy_horny_roh_gps_longitude = models.FloatField(null=False, blank = False, default = None)
    pravy_spodny_roh_gps_latitude = models.FloatField(null=False, blank = False, default = None)
    pravy_spodny_roh_gps_longitude = models.FloatField(null=False, blank = False, default = None)
    
    class Meta:
        verbose_name_plural = 'Mapy'
        #app_label ="mapa"
        
    def __str__(self):
        return str(self.podujatie_id)
    
    def __repr__(self):
        return str(self.podujatie_id)