from django.contrib import admin
from models import SkupinaPodia
# Register your models here.

class SkupinaPodiaAdmin(admin.ModelAdmin):
    model = SkupinaPodia
    list_display = ['skupina_id', 'podium_id', 'cas_od', 'cas_do']
    search_fields = ['skupina_id', 'podium_id']
    
    
admin.site.register(SkupinaPodia, SkupinaPodiaAdmin)