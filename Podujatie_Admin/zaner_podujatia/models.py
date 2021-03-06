from __future__ import unicode_literals

from django.db import models
from zaner.models import Zaner
from Podujatie.models import Podujatie

# Create your models here.

class ZanerPodujatia(models.Model):
    podujatie = models.ForeignKey(Podujatie, null=False, blank = False, default = None)
    zaner = models.ForeignKey(Zaner, null=False, blank = False, default = None)
    
    class Meta:
        verbose_name_plural = 'Zanre Podujati'
        #app_label ="podujatie"
        
    def __str__(self):
        return str(self.zaner_id) + " " + str(self.podujatie_id) 
    
    def __repr__(self):
        return str(self.zaner_id) + " " + str(self.podujatie_id) 