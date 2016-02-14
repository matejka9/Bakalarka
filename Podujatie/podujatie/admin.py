from django.contrib import admin
from models import Podujatie

# Register your models here.

class PodujatieAdmin(admin.ModelAdmin):
    model = Podujatie
    list_display = ['administrator_id', 'nazov', 'typ', 'datum_od']
    list_filter = ['administrator_id', 'typ']
    search_fields = ['nazov']
    
    
admin.site.register(Podujatie, PodujatieAdmin)