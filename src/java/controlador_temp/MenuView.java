/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador_temp;

import entidad.PermisoAcceso;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Elena
 */
@Named(value = "menuView")
@RequestScoped
public class MenuView {

    private MenuModel model;
    private static final String PATH_LOCAL = "/Inventario/faces";

    private List<PermisoAcceso> listPermisosUsuarioLogeado = null;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        listPermisosUsuarioLogeado = (List<PermisoAcceso>) fc.getExternalContext().getSessionMap().get("sesionListPermisosUsuarioLogeado");
//        System.out.println("Menu: Cantidad permisos " + listPermisosUsuarioLogeado.size());

        model = new DefaultMenuModel();
        DefaultMenuItem item = DefaultMenuItem.builder()
                .value("Home")
                .icon("pi pi-home")
                .ajax(false)
                .url(PATH_LOCAL + "/bienvenida.xhtml")
                .build();

        model.getElements().add(item);

        //First submenu
        DefaultSubMenu firstSubmenu = DefaultSubMenu.builder()
                .label("Gestión de Acceso")
                .icon("pi pi-bars")
                .build();

        //Second submenu
        DefaultSubMenu secondSubmenu = DefaultSubMenu.builder()
                .label("Gestión de información")
                .icon("pi pi-paperclip")
                .build();

        for (PermisoAcceso pa : listPermisosUsuarioLogeado) {
            item = DefaultMenuItem.builder()
                    .value(pa.getIdOpcionsistema().getNombreOpcionsistema())
                    .icon(pa.getIdOpcionsistema().getIconoOpcionsistema())
                    .ajax(false)
                    .url(PATH_LOCAL + pa.getIdOpcionsistema().getUrlOpcionsistema())
                    .build();

            if (pa.getIdOpcionsistema().getIdOpcionsistema() <= 3) {
                firstSubmenu.getElements().add(item);
            } else {
                secondSubmenu.getElements().add(item);
            }
        }

        if (firstSubmenu.getElements().size() > 0) {
            model.getElements().add(firstSubmenu);
        }
        if (secondSubmenu.getElements().size() > 0) {
            model.getElements().add(secondSubmenu);
        }
    }

    public MenuModel getModel() {
        return model;
    }

//    public void redirect() throws IOException {
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.redirect(ec.getRequestContextPath());
//    }
//
//    public void save() {
//        addMessage("Save", "Data saved");
//    }
//
//    public void update() {
//        addMessage("Update", "Data updated");
//    }
//
//    public void delete() {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Delete", "Data deleted");
//        FacesContext.getCurrentInstance().addMessage(null, message);
//    }
//
//    public void addMessage(String summary, String detail) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
//        FacesContext.getCurrentInstance().addMessage(null, message);
//    }
}
