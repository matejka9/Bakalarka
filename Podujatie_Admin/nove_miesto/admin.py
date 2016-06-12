from django.contrib import admin

from models import NoveMiesto
# Register your models here.

class NoveMiestoAdmin(admin.ModelAdmin):
    model = NoveMiesto
    list_display = ['mapa_id', 'nazov']
    #search_fields = ['mapa_id', 'nazov']
    
    
admin.site.register(NoveMiesto, NoveMiestoAdmin)