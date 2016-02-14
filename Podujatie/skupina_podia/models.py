from __future__ import unicode_literals

from django.db import models

from skupina.models import Skupina
from podium.models import Podium

# Create your models here.

class SkupinaPodia(models.Model):
    skupina_id = models.ForeignKey(Skupina, null=False, blank = False, default = None)
    podium_id = models.ForeignKey(Podium, null=False, blank = False, default = None)
    detail = models.TextField(max_length = 10000, null=True)
    cas_od = models.DateTimeField(null = False, blank = False, default = None)
    cas_do = models.DateTimeField(null = False, blank = False, default = None)
    
    class Meta:
        verbose_name_plural = 'Skupiny podia'
        app_label ="podium"
        
    def __str__(self):
        return str(self.skupina_id) + " " + str(self.skupina_id)
    
    def __repr__(self):
        return str(self.skupina_id) + " " + str(self.skupina_id)