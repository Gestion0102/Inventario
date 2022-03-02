package converter;

import entidad.OpcionSistema;
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

@FacesConverter("opcionSistemaPickListConverter")
public class OpcionSistemaPickListConverter implements Converter {

//	private static final Logger LOG = LoggerFactory.getLogger(OpcionSistemaPickListConverter.class);
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
                string = String.valueOf(((OpcionSistema) object).getIdOpcionsistema());
            } catch (ClassCastException cce) {
                throw new ConverterException();
            }
        }
        return string;
    }

    @SuppressWarnings("unchecked")
    private OpcionSistema getObjectFromUIPickListComponent(UIComponent component, String value) {
        final DualListModel<OpcionSistema> dualList;
        try {
            dualList = (DualListModel<OpcionSistema>) ((PickList) component).getValue();
            OpcionSistema team = getObjectFromList(dualList.getSource(), Integer.valueOf(value));
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

    private OpcionSistema getObjectFromList(final List<?> list, final Integer identifier) {
        for (final Object object : list) {
            final OpcionSistema team = (OpcionSistema) object;
            if (team.getIdOpcionsistema().equals(identifier)) {
                return team;
            }
        }
        return null;
    }
}
