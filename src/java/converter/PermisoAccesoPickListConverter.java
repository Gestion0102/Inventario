package converter;

import entidad.PermisoAcceso;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@FacesConverter("permisoAccesoPickListConverter")
public class PermisoAccesoPickListConverter implements Converter {

//	private static final Logger LOG = LoggerFactory.getLogger(PermisoAccesoPickListConverter.class);
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//		LOG.trace("String value: {}", value);

        return getObjectFromUIPickListComponent(component, value);
    }

    public String getAsString(FacesContext context, UIComponent component, Object object) {
        String string;
//		LOG.trace("Object value: {}", object);
        if (object == null) {
            string = "";
        } else {
            try {
                string = String.valueOf(((PermisoAcceso) object).getIdPermisoacceso());
            } catch (ClassCastException cce) {
                throw new ConverterException();
            }
        }
        return string;
    }

    @SuppressWarnings("unchecked")
    private PermisoAcceso getObjectFromUIPickListComponent(UIComponent component, String value) {
        final DualListModel<PermisoAcceso> dualList;
        try {
            dualList = (DualListModel<PermisoAcceso>) ((PickList) component).getValue();
            PermisoAcceso team = getObjectFromList(dualList.getSource(), Integer.valueOf(value));
            if (team == null) {
                team = getObjectFromList(dualList.getTarget(), Integer.valueOf(value));
            }

            return team;
        } catch (ClassCastException cce) {
            throw new ConverterException();
        } catch (NumberFormatException nfe) {
            throw new ConverterException();
        }
    }

    private PermisoAcceso getObjectFromList(final List<?> list, final Integer identifier) {
        for (final Object object : list) {
            final PermisoAcceso team = (PermisoAcceso) object;
            if (team.getIdPermisoacceso().equals(identifier)) {
                return team;
            }
        }
        return null;
    }
}
