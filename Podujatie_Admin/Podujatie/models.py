from __future__ import unicode_literals

from django.db import models
from django.contrib.auth.models import User

# Create your models here.

class Podujatie(models.Model):
    typ_choices = (
                   ('Festival', 'Festival'),
                   ('Hody', 'Hody')
                   )
    
    administrator = models.ForeignKey(User, null=False, blank = False, default = None)
    nazov = models.CharField(max_length = 255, default = None, null=False, blank=False)
    typ = models.CharField(max_length = 255, choices = typ_choices)
    datum_od = models.DateField(null = False, blank = False, default = None)
    datum_do = models.DateField(null = False, blank = False, default = None)
    
    class Meta:
        verbose_name_plural = 'Podujatia'
        
    def __str__(self):
        return self.nazov
    
    def __repr__(self):
        return self.nazov