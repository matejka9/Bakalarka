from django.contrib import admin

from models import Stanok
# Register your models here.

class StanokAdmin(admin.ModelAdmin):
    model = Stanok
    #list_display = ['mapa_id', 'nazov']
    #search_fields = ['mapa_id', 'nazov']
    
    
admin.site.register(Stanok, StanokAdmin)