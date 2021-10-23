package com.mf.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/***
 * SMAX相关Restful URL
 */
public class Constants {

    private static Logger log = LoggerFactory.getLogger(Constants.class);

    public static  String endPoint = "https://us1-smax.saas.microfocus.com/auth/authentication-endpoint/authenticate/login?TENANTID={TENANTID}";

    //EntityModel
    public static  String tenantID="";
    public static  String RegisteredForActualService="";
    public static  String agent="";
    public static  String username = "";
    public static  String password = "";
    public static  String servicegroup="";

    public static String getDomainname() {
        return domainname;
    }

    public static void setDomainname(String domainname) {
        Constants.domainname = domainname;
    }

    public static  String IncidentModel="";
    public static  String domainname="";

    public static  String requestUrl="https://{DOMAINNAME}/rest/{TENANTID}/ems/bulk";
    public static  String theincidentUrl="https://{DOMAINNAME}/rest/{TENANTID}/ems/Incident/{TICKETID}?layout=FULL_LAYOUT,RELATION_LAYOUT.item";
    public static  String queryincidentUrl="https://{DOMAINNAME}/rest/{TENANTID}/ems/Incident/TICKETID?layout=UserOptionsName,Ola.Id,Ola.Id,Ola.DisplayLabel,Ola.Flavor,Ola.Type,IncidentToCompany.Id,IncidentToCompany.Name,IncidentToCompany.Id,IncidentToCompany.DisplayLabel,IncidentToCompany.CompanyLogo,IncidentToCompany.Tenant,IncidentToCompany.Code,IncidentToCompany.ManagedCustomer,ClosureCategory.Id,ClosureCategory.DisplayLabel,ClosureCategory.Level1Parent,ClosureCategory.Level2Parent,Sla.Id,Sla.Id,Sla.DisplayLabel,Sla.Flavor,Sla.Type,SLTAggregatedStatus,RegisteredForLocation.Id,RegisteredForLocation.Name,RegisteredForLocation.DisplayLabel,RegisteredForLocation.DisplayName,RegisteredForLocation.FullName,SolvedTime,ContactPerson.Id,ContactPerson.Name,ContactPerson.Avatar,ContactPerson.Location,ContactPerson.IsVIP,ContactPerson.OrganizationalGroup,ContactPerson.Upn,ExplicitStakeholders,NextTargetTime,DisplayLabel,Name,TasksWorkspace,FirstTouch,ExpertAssignee.Id,ExpertAssignee.Name,ExpertAssignee.Avatar,ExpertAssignee.Location,ExpertAssignee.IsVIP,ExpertAssignee.OrganizationalGroup,ExpertAssignee.Upn,Escalated,OwnedByPerson.Id,OwnedByPerson.Name,OwnedByPerson.Avatar,OwnedByPerson.Location,OwnedByPerson.IsVIP,OwnedByPerson.OrganizationalGroup,OwnedByPerson.Upn,LastUpdateTime,ProblemCandidate,SLT.Id,SLT.Id,SLT.DisplayLabel,SLT.TargetDate,SLT.SLATargetDate,SLT.OLATargetDate,DetectedEntities,EntitySettings,CaseExchangeExternalOperation,ImpactStart,PreferredContactMethod,CompletionCode,RegisteredForServiceComponent.Id,RegisteredForServiceComponent.DisplayLabel,RegisteredForServiceComponent.SubType,Status,ServiceDeskGroup.Id,ServiceDeskGroup.Name,SolvedByPerson.Id,SolvedByPerson.Name,SolvedByPerson.Avatar,SolvedByPerson.Location,SolvedByPerson.IsVIP,SolvedByPerson.OrganizationalGroup,SolvedByPerson.Upn,PhaseId,DataDomains,IsDeleted,KnowledgeCandidate,CaseExchangeEnabled,Priority,EntityModel.Id,EntityModel.DisplayLabel,EntityModel.Description,EntityModel.ChangeWorkflowType,EntityModel.ModelType,AssignedPerson.Id,AssignedPerson.Name,AssignedPerson.Avatar,AssignedPerson.Location,AssignedPerson.IsVIP,AssignedPerson.OrganizationalGroup,AssignedPerson.Upn,IncidentResolutionDeviceDetection,LinkedRequestCompletionCode,GlobalId,TasksExecutionPlanValidate,AllStakeholders,Expire,Active,TasksInternalData,ExternalProcessReference,ExpertGroup.Id,ExpertGroup.Name,CurrentAssignment,Id,TasksExecutionPlanEscalate,DescriptionDeviceDetection,UserOptions,Description,RegisteredForActualService.Id,RegisteredForActualService.DisplayLabel,RegisteredForActualService.SubType,Category.Id,Category.DisplayLabel,Category.Level1Parent,Category.Level2Parent,ClosedByPerson.Id,ClosedByPerson.Name,ClosedByPerson.Avatar,ClosedByPerson.Location,ClosedByPerson.IsVIP,ClosedByPerson.OrganizationalGroup,ClosedByPerson.Upn,MajorIncident,Source,RecordedByPerson.Id,RecordedByPerson.Name,RecordedByPerson.Avatar,RecordedByPerson.Location,RecordedByPerson.IsVIP,RecordedByPerson.OrganizationalGroup,RecordedByPerson.Upn,RequestedByPerson.Id,RequestedByPerson.Name,RequestedByPerson.Avatar,RequestedByPerson.Location,RequestedByPerson.IsVIP,RequestedByPerson.OrganizationalGroup,RequestedByPerson.Upn,CostCenter.Id,CostCenter.Id,CostCenter.DisplayLabel,RegisteredForInfrastructurePeripheral.Id,RegisteredForInfrastructurePeripheral.Id,RegisteredForInfrastructurePeripheral.DisplayLabel,RegisteredForInfrastructurePeripheral.SubType,RegisteredForInfrastructurePeripheral.DefaultWarrantyContract,RegisteredForInfrastructurePeripheral.DefaultMaintenanceContract,EmsCreationTime,TasksExecutionPlanClassify,LastActiveTime,ImpactEnd,NumberOfAttachments,IncidentSettings,Solution,ExpectedResolutionTime,Comments,ShortRemark_c,NumberOfRelatedRecords,TasksExecutionPlanInitialSupport,UserOptionsType,LastUpdatedByPerson.Id,LastUpdatedByPerson.Name,LastUpdatedByPerson.Avatar,LastUpdatedByPerson.Location,LastUpdatedByPerson.IsVIP,LastUpdatedByPerson.OrganizationalGroup,LastUpdatedByPerson.Upn,Lifetime,ImpactScope,CloseTime,IncidentAttachments,FirstLine,ProcessId,ModelWorkflow,Urgency,AssignedGroup.Id,AssignedGroup.Name";
    public static  String therequestUrl="https://{DOMAINNAME}/rest/{TENANTID}/ems/Request/{TICKETID}?layout=FULL_LAYOUT,RELATION_LAYOUT.item";
    public static  String personUrl="https://{DOMAINNAME}/rest/{TENANTID}/ems/Person/PERSONID?layout=Name";
    public static  String groupUrl="https://{DOMAINNAME}/rest/{TENANTID}/ems/PersonGroup/GROUPID?layout=GroupCompany.Id,GroupCompany.Name,GroupCompany.Id,GroupCompany.DisplayLabel,GroupCompany.CompanyLogo,GroupCompany.Tenant,GroupCompany.Code,GroupCompany.ManagedCustomer,Locale,AssignmentStrategy,Ownership,Owner.Id,Owner.Name,Owner.Avatar,Owner.Location,Owner.IsVIP,Owner.OrganizationalGroup,Owner.Upn,Email,OnCallSchedule,ParentGroup.Id,ParentGroup.Name,Dispatcher.Id,Dispatcher.Name,Dispatcher.Avatar,Dispatcher.Location,Dispatcher.IsVIP,Dispatcher.OrganizationalGroup,Dispatcher.Upn,PrimaryDataDomain,ExplicitStakeholders,PracticeAreas,Name,Upn,CostCenter.Id,CostCenter.Id,CostCenter.DisplayLabel,LastUpdateTime,EmsCreationTime,OwnerBackup.Id,OwnerBackup.Name,OwnerBackup.Avatar,OwnerBackup.Location,OwnerBackup.IsVIP,OwnerBackup.OrganizationalGroup,OwnerBackup.Upn,Phone,HomeLocation,Status,PhaseId,IsDeleted,DataDomains,DistinguishedName,Comments,ReportingTarget.Id,ReportingTarget.Name,HasAuthorizationRecord,WorkSchedule.Id,WorkSchedule.Name,WorkSchedule.Id,WorkSchedule.DisplayLabel,WorkSchedule.TimePeriodType,AllStakeholders,Lifetime,Expire,GroupType,VelocityPerDay,DevParticipationPercentage,IsExternal,IsReportingTarget,IsDevTeam,Id,BelongsToExternalSystem.Id,BelongsToExternalSystem.SystemId,ProcessId";


    public static void Initialize() {

        try {


            ClassPathResource resource = new ClassPathResource("application.properties");
            Properties p = PropertiesLoaderUtils.loadProperties(new EncodedResource(resource, "UTF-8"));

            Constants.agent = p.getProperty("SMAX.agent").trim();
            Constants.tenantID = p.getProperty("SMAX.TENANTID").trim();
            Constants.username = p.getProperty("SMAX.username").trim();
            Constants.password = p.getProperty("SMAX.password").trim();
            Constants.RegisteredForActualService = p.getProperty("SMAX.RegisteredForActualService").trim();
            Constants.servicegroup = p.getProperty("SMAX.servicegroup").trim();
            Constants.IncidentModel = p.getProperty("SMAX.IncidentModel").trim();
            Constants.domainname=p.getProperty("SMAX.domainname").trim();


            Constants.endPoint = Constants.endPoint.replace("{TENANTID}",Constants.tenantID).replace("{DOMAINNAME}",Constants.domainname);
            Constants.requestUrl = Constants.requestUrl.replace("{TENANTID}",Constants.tenantID).replace("{DOMAINNAME}",Constants.domainname);
            Constants.theincidentUrl = Constants.theincidentUrl.replace("{TENANTID}",Constants.tenantID).replace("{DOMAINNAME}",Constants.domainname);
            Constants.therequestUrl = Constants.therequestUrl.replace("{TENANTID}",Constants.tenantID).replace("{DOMAINNAME}",Constants.domainname);
            Constants.personUrl = Constants.personUrl.replace("{TENANTID}",Constants.tenantID).replace("{DOMAINNAME}",Constants.domainname);
            Constants.groupUrl = Constants.groupUrl.replace("{TENANTID}",Constants.tenantID).replace("{DOMAINNAME}",Constants.domainname);
            Constants.queryincidentUrl=Constants.queryincidentUrl.replace("{TENANTID}",Constants.tenantID).replace("{DOMAINNAME}",Constants.domainname);



        } catch (IOException e1) {
            log.error(e1.getMessage());
            e1.printStackTrace();
        }
    }


}  