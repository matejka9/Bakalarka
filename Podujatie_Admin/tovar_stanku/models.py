from __future__ import unicode_literals

from django.db import models

from stanok.models import Stanok
from tovar.models import Tovar
# Create your models here.

class TovarStanku(models.Model):
    stanok_id = models.ForeignKey(Stanok, null=False, blank = False, default = None)
    tovar_id = models.ForeignKey(Tovar, null=False, blank = False, default = None)
    detail = models.TextField(max_length = 10000, null=True)
    cena = models.FloatField(null=False, blank = False, default = None)
    
    class Meta:
        verbose_name_plural = 'Tovar Stankov'
        #app_label ="stanok"
        
    def __str__(self):
        return str(self.stanok_id) + " " + str(self.tovar_id)
    
    def __repr__(self):
        return str(self.stanok_id) + " " + str(self.tovar_id)