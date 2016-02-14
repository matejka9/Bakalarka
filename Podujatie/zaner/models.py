from __future__ import unicode_literals

from django.db import models

# Create your models here.

class Zaner(models.Model):    
    nazov = models.CharField(max_length = 50, default = None, null=False, blank=False)
    
    class Meta:
        verbose_name_plural = 'Zanre'
        app_label ="podujatie"
        
    def __str__(self):
        return self.nazov
    
    def __repr__(self):
        return self.nazov