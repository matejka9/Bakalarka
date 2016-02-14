from django.contrib import admin

from models import Skupina
# Register your models here.

class SkupinaAdmin(admin.ModelAdmin):
    model = Skupina
    list_display = ['nazov']
    search_fields = ['nazov']
    
    
admin.site.register(Skupina, SkupinaAdmin)