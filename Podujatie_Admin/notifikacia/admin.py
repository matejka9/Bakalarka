from django.contrib import admin

from models import Notifikacia
# Register your models here.

class NotifikaciaAdmin(admin.ModelAdmin):
    model = Notifikacia
    list_display = ['mapa', 'nadpis']
    #search_fields = ['mapa_id', 'nazov']
    
    
admin.site.register(Notifikacia, NotifikaciaAdmin)
