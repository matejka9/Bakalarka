from django.contrib import admin

from models import ZanerPodujatia

# Register your models here.

class ZanerPodujatiaAdmin(admin.ModelAdmin):
    model = ZanerPodujatia
    list_display = ['podujatie_id', 'zaner_id']
    list_filter = ['zaner_id']
    search_fields = ['podujatie_id', 'zaner_id']
    
admin.site.register(ZanerPodujatia, ZanerPodujatiaAdmin)