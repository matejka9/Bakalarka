from django.db import models
from mapa.models import Mapa

# Create your models here.

class Notifikacia(models.Model):    
    mapa = models.ForeignKey(Mapa, null=False, blank = False, default = None)
    nadpis = models.CharField(max_length = 255, default = None, null=False, blank=False)
    text = models.CharField(max_length = 255, default = None, null=False, blank=False)
    detail = models.TextField(max_length = 10000, default = None, null=False, blank=False)
    
    class Meta:
        verbose_name_plural = 'Notifikacie'
        
    def __str__(self):
        return self.nadpis
    
    def __repr__(self):
        return self.nadpis
