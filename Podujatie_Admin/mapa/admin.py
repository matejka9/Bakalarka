from django.contrib import admin

from models import Mapa
# Register your models here.

class MapaAdmin(admin.ModelAdmin):
    model = Mapa
    list_display = ['podujatie_id', 'typ']
    list_filter = ['podujatie_id', 'typ']
    search_fields = ['podujatie_id']
    
admin.site.register(Mapa, MapaAdmin)