from __future__ import unicode_literals

from django.db import models

# Create your models here.
class Tovar(models.Model):
    nazov = models.CharField(max_length = 255, default = None, null=False, blank=False)
    foto = models.ImageField(null=True)
    
    class Meta:
        verbose_name_plural = 'Tovary'
        #app_label ="stanok"
        
    def __str__(self):
        return self.nazov
    
    def __repr__(self):
        return self.nazov