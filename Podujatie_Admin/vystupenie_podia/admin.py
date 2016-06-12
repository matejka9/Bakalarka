from django.contrib import admin
from models import VystupeniePodia
# Register your models here.

class VystupeniePodiaAdmin(admin.ModelAdmin):
    model = VystupeniePodia
    list_display = ['vystupenie_id', 'podium_id', 'cas_od', 'cas_do']
    search_fields = ['vystupenie_id', 'podium_id']
    
    
admin.site.register(VystupeniePodia, VystupeniePodiaAdmin)