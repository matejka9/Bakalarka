from __future__ import unicode_literals

from django.db import models

from vystupenie.models import Vystupenie
from podium.models import Podium

# Create your models here.

class VystupeniePodia(models.Model):
    vystupenie = models.ForeignKey(Vystupenie, null=False, blank = False, default = None)
    podium = models.ForeignKey(Podium, null=False, blank = False, default = None)
    detail = models.TextField(max_length = 10000, null=True)
    cas_od = models.DateTimeField(null = False, blank = False, default = None)
    cas_do = models.DateTimeField(null = False, blank = False, default = None)
    
    class Meta:
        verbose_name_plural = 'Vystupenia podia'
        #app_label ="podium"
        
    def __str__(self):
        return str(self.vystupenie_id) + " " + str(self.podium_id)
    
    def __repr__(self):
        return str(self.vystupenie_id) + " " + str(self.podium_id)