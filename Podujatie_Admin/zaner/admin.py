from django.contrib import admin

from models import Zaner

# Register your models here.

class ZanerAdmin(admin.ModelAdmin):
    model = Zaner
    list_display = ['nazov']
    search_fields = ['nazov']
    
admin.site.register(Zaner, ZanerAdmin)