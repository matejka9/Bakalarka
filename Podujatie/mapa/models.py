from __future__ import unicode_literals

from django.db import models
from podujatie.models import Podujatie

# Create your models here.

class Mapa(models.Model):
    typ_choices = (
                   (0, 'Hlavna'),
                   (1, 'Vedlajsia')
                   )
    
    podujatie_id = models.ForeignKey(Podujatie, null=False, blank = False, default = None)
    mapa = models.ImageField(default = None, null=False, blank=False)
    typ = models.CharField(max_length = 1, choices = typ_choices)
    lavy_horny_roh_gps_latitude = models.FloatField(null=False, blank = False, default = None)
    lavy_horny_roh_gps_longitude = models.FloatField(null=False, blank = False, default = None)
    pravy_spodny_roh_gps_latitude = models.FloatField(null=False, blank = False, default = None)
    pravy_spodny_roh_gps_longitude = models.FloatField(null=False, blank = False, default = None)
    
    class Meta:
        verbose_name_plural = 'Mapy'
        app_label ="podujatie"
        
    def __str__(self):
        return self.typ + " " + str(self.podujatie_id)
    
    def __repr__(self):
        return self.typ + " " + str(self.podujatie_id)