from django.contrib import admin

from models import Podium
# Register your models here.

class PodiumAdmin(admin.ModelAdmin):
    model = Podium
    #list_display = ['mapa_id', 'nazov']
    #search_fields = ['mapa_id', 'nazov']
    
    
admin.site.register(Podium, PodiumAdmin)