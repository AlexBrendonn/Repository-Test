package com.saa.web;

import com.saa.web.resource.filter.AuthenticationFilter;
import com.saa.web.resource.filter.HeaderFilter;
import com.saa.web.resource.mapper.*;
import com.saa.web.router.authentication.AuthenticationRouter;
import com.saa.web.router.authentication.CompanyRouter;
import com.saa.web.router.authentication.StatusRouter;
import com.saa.web.router.held.*;
import com.saa.web.router.manager.DocumentHeaderRouter;
import com.saa.web.router.manager.DocumentInstallmentRouter;
import com.saa.web.router.manager.DocumentItemRouter;
import com.saa.web.router.register.*;
import com.saa.web.router.tillage.*;
import com.saa.web.router.tributary.OperationRuleRouter;
import com.saa.web.router.tributary.OperationTypeRouter;
import com.saa.web.router.tributary.PaymentTypeRouter;
import com.saa.web.router.tributary.RestrictionTaxRouter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/v1")
public class Main extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet hash = new HashSet<Class<?>>();

        // <editor-fold defaultstate="collapsed" desc="Listener">
        hash.add(HeaderFilter.class);
        //hash.add(HibernateSessionManager.OpenSession.class);
        //hash.add(HibernateSessionManager.CloseSession.class);
        hash.add(AuthenticationFilter.class);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Mappers">
        hash.add(CommonExceptionMapper.class);
        hash.add(ForbiddenMapper.class);
        hash.add(BadRequestMapper.class);
        hash.add(EntityNotFoundMapper.class);
        hash.add(InternalServerErrorMapper.class);
        hash.add(NotAuthorizedMapper.class);
        hash.add(NotFoundMapper.class);
        hash.add(PersistenceMapper.class);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Routers">

        //Authentication
        hash.add(AuthenticationRouter.class);
        hash.add(CompanyRouter.class);
        hash.add(StatusRouter.class);

        //Held
        hash.add(CstRouter.class);
        hash.add(CfopRouter.class);
        hash.add(SpedRouter.class);
        hash.add(CityRouter.class);
        hash.add(StateRouter.class);
        hash.add(CountryRouter.class);

        //Manager
        hash.add(DocumentHeaderRouter.class);
        hash.add(DocumentInstallmentRouter.class);
        hash.add(DocumentItemRouter.class);

        //Register
        hash.add(PersonGroupRouter.class);
        hash.add(PersonRouter.class);
        hash.add(ProductGroupRouter.class);
        hash.add(ProductRouter.class);
        hash.add(UnitRouter.class);
        hash.add(VehicleRouter.class);

        //Tillage
        hash.add(ApplianceFieldTypeRouter.class);
        hash.add(ApplianceVehicleTypeRouter.class);
        hash.add(ContractTypeRouter.class);
        hash.add(CycleRouter.class);
        hash.add(DiscountTypeRouter.class);
        hash.add(ExchangeConfigurationRouter.class);
        hash.add(HarvestRouter.class);
        hash.add(HarvestConfigurationRouter.class);
        hash.add(PlotRouter.class);
        hash.add(PlotTypeRouter.class);
        hash.add(ServiceTypeRouter.class);
        hash.add(WorkOnVehicleRouter.class);

        //Tributary
        hash.add(RestrictionTaxRouter.class);
        hash.add(OperationRuleRouter.class);
        hash.add(OperationTypeRouter.class);
        hash.add(PaymentTypeRouter.class);

        // </editor-fold>

        return hash;
    }
}
