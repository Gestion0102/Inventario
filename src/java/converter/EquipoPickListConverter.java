package converter;

import entidad.Equipo;
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

@FacesConverter("equipoPickListConverter")
public class EquipoPickListConverter implements Converter {

//	private static final Logger LOG = LoggerFactory.getLogger(OpcionSistemaPickListConverter.class);
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//		LOG.trace("String value: {}", value);

        return getObjectFromUIPickListComponent(component, value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        String string;
//		LOG.trace("Object value: {}", object);
        if (object == null) {
            string = "";
        } else {
            try {
                string = String.valueOf(((Equipo) object).getIdEquipo());
            } catch (ClassCastException cce) {
                throw new ConverterException();
            }
        }
        return string;
    }

    @SuppressWarnings("unchecked")
    private Equipo getObjectFromUIPickListComponent(UIComponent component, String value) {
        final DualListModel<Equipo> dualList;
        try {
            dualList = (DualListModel<Equipo>) ((PickList) component).getValue();
            Equipo team = getObjectFromList(dualList.getSource(), Integer.valueOf(value));
            if (team == null) {
                team = getObjectFromList(dualList.getTarget(), Integer.valueOf(value));
            }

            return team;
        } catch (ClassCastException | NumberFormatException cce) {
            throw new ConverterException();
        }
    }

    private Equipo getObjectFromList(final List<?> list, final Integer identifier) {
        for (final Object object : list) {
            final Equipo team = (Equipo) object;
            if (team.getIdEquipo().equals(identifier)) {
                return team;
            }
        }
        return null;
    }
}
