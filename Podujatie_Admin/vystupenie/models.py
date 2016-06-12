from __future__ import unicode_literals

from django.db import models

# Create your models here.

class Vystupenie(models.Model):
    nazov = models.CharField(max_length = 255, default = None, null=False, blank=False)
    
    class Meta:
        verbose_name_plural = 'Vystupenia'
        
    def __str__(self):
        return self.nazov
    
    def __repr__(self):
        return self.nazov