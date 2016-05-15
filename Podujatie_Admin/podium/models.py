from __future__ import unicode_literals

from django.db import models
from mapa.models import Mapa

# Create your models here.

class Podium(models.Model):   
    mapa_id = models.ForeignKey(Mapa, null=False, blank = False, default = None)
    nazov = models.CharField(max_length = 255, default = None, null=False, blank=False)
    lavy_horny_roh_x = models.FloatField(null = False, blank = False, default = None)
    lavy_horny_roh_y = models.FloatField(null = False, blank = False, default = None)
    pravy_spodny_roh_x = models.FloatField(null = False, blank = False, default = None)
    pravy_spodny_roh_y = models.FloatField(null = False, blank = False, default = None)
    
    class Meta:
        verbose_name_plural = 'Podia'
        #app_label ="podium"
        
    def __str__(self):
        return self.nazov
    
    def __repr__(self):
        return self.nazov