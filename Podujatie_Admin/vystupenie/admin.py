from django.contrib import admin

from models import Vystupenie
# Register your models here.

class VystupenieAdmin(admin.ModelAdmin):
    model = Vystupenie
    list_display = ['nazov']
    search_fields = ['nazov']
    
    
admin.site.register(Vystupenie, VystupenieAdmin)