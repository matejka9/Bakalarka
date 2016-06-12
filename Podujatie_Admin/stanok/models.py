from __future__ import unicode_literals

from django.db import models
from mapa.models import Mapa

# Create your models here.

class Stanok(models.Model):    
    mapa = models.ForeignKey(Mapa, null=False, blank = False, default = None)
    nazov = models.CharField(max_length = 255, default = None, null=False, blank=False)
    longitude = models.FloatField(null=False, blank = False, default = None)
    latidute = models.FloatField(null=False, blank = False, default = None)
    
    class Meta:
        verbose_name_plural = 'Stanky'
        
    def __str__(self):
        return self.nazov
    
    def __repr__(self):
        return self.nazov