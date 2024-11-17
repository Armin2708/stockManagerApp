import {
    Alert,
    AlertIcon,
    Box, Button,
    FormLabel,
    Input, Select,
    Stack,
    Tab,
    TabList,
    TabPanel,
    TabPanels,
    Tabs,
    Text
} from "@chakra-ui/react";
import {saveInsuranceClient} from "../services/insuranceClient.js";
import {Form, Formik, useField, useFormikContext} from 'formik';
import * as Yup from 'yup';
import {errorNotification, successNotification} from "../services/notification.js";

import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

import 'react-phone-number-input/style.css'
import PhoneInput from 'react-phone-number-input'

import Autocomplete from "react-google-autocomplete";
import AddressSelector from "./AddressSelector.jsx";
import PlacesAutocomplete from 'react-places-autocomplete';



const MyTextInput = ({label, ...props}) => {

    const [field, meta] = useField(props);
    return (
        <Box>
            <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
            <Input className="text-input" {...field} {...props} />
            {meta.touched && meta.error ? (
                <Alert className="error" status={"error"} mt={2}>
                    <AlertIcon/>
                    {meta.error}
                </Alert>
            ) : null}
        </Box>
    );
};

const MySelect = ({label, ...props}) => {
    const [field, meta] = useField(props);
    return (
        <Box>
            <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
            <Select {...field} {...props} />
            {meta.touched && meta.error ? (
                <Alert className="error" status={"error"} mt={2}>
                    <AlertIcon/>
                    {meta.error}
                </Alert>
            ) : null}
        </Box>
    );
};

const MyDatePicker = ({label,...props}) => {
    const [field, meta,helpers] = useField(props);

    const { value } = meta;
    const { setValue } = helpers;
    return (
        <Box>
            <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
            <Box borderWidth='1px' borderRadius='5px'>
                <DatePicker
                    {...field}
                    selected={value}
                    format={"MM/dd/yyyy"}
                    onChange={(date) => {
                        const formatDate = (date.getMonth()+1)+"/"+date.getDate()+"/"+date.getFullYear()
                        setValue(formatDate)
                        console.log(formatDate)
                    }}
                />
            </Box>
            {meta.touched && meta.error ? (
                <Alert className="error" status={"error"} mt={2}>
                    <AlertIcon/>
                    {meta.error}
                </Alert>
            ) : null}
        </Box>
    );
};

const MyPhoneInput = ({label,...props}) =>{
    const [field, meta,helpers] = useField(props);

    const { value } = meta;
    const { setValue } = helpers;
    return(
        <Box>
            <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
                <Box borderWidth='1px' borderRadius='5px'>
                    <PhoneInput
                        {...field}
                        placeholder={props.placeholder}
                        value={value}
                        onChange={(date) => setValue(date)}
                    />
                </Box>
            {meta.touched && meta.error ? (
                <Alert className="error" status={"error"} mt={2}>
                    <AlertIcon/>
                    {meta.error}
                </Alert>
            ) : null}
        </Box>
    )
}

const MyAddressInput= ({ label, setFieldValue, ...props }) => {
    const [field, meta] = useField(props.name);

    return (
        <Box mt={4}>
            <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
            <Box borderWidth='1px' borderRadius='5px'>
                <PlacesAutocomplete
                    value={field.value}
                    onChange={value => setFieldValue(props.name, value)}
                    onSelect={(value, placeId) => {
                        const service = new google.maps.places.PlacesService(document.createElement("div"));
                        service.getDetails({ placeId: placeId }, function (place, status) {
                            if (status === google.maps.places.PlacesServiceStatus.OK) {
                                let streetNumber = '';
                                let route = '';

                                place.address_components.forEach((c) => {
                                    if (c.types.includes("street_number")) {
                                        streetNumber = c.long_name;
                                    } else if (c.types.includes("route")) {
                                        route = c.long_name;
                                    } else if (c.types.includes("locality")) {
                                        setFieldValue("city", c.long_name);
                                    } else if (c.types.includes("administrative_area_level_1")) {
                                        setFieldValue("state", c.long_name);
                                    } else if (c.types.includes("postal_code")) {
                                        setFieldValue("postalCode", c.short_name);
                                    } else if (c.types.includes("country")) {
                                        setFieldValue("country", c.long_name);
                                    }
                                });

                                // Only the defined streetNumber and route
                                const streetAddress = `${streetNumber} ${route}`;
                                setFieldValue("address", streetAddress);
                            }
                        });
                    }}
                >
                    {({ getInputProps, suggestions, getSuggestionItemProps, loading }) => (
                        <Box>
                            <Input
                                {...getInputProps({ placeholder: 'Address' })}
                            />
                            {loading ? <div>Loading...</div> : null}
                            {suggestions.map((suggestion, index) => {
                                const style = {
                                    backgroundColor: suggestion.active ? "#41b6e6" : "#fff"
                                    // implement more styling as needed
                                };

                                return (
                                    <Box {...getSuggestionItemProps(suggestion, { style })} key={index}>
                                        {suggestion.description}
                                    </Box>
                                );
                            })}
                        </Box>
                    )}
                </PlacesAutocomplete>
            </Box>
            {meta.touched && meta.error ? (
                <Alert className="error" status={"error"} mt={2}>
                    <AlertIcon/>
                    {meta.error}
                </Alert>
            ) : null}
        </Box>
    );
};

const CreateClientForm = () => {
    return(
        <>
            <Formik
                initialValues={{
                    email:'',
                    firstName: '',
                    lastName: '',
                    gender: '',
                    birthDate:'08/27/2003',
                    phoneNumber:'',
                    country: '',
                    state: '',
                    city: '',
                    postalCode: '',
                    address: '',
                    ageRisk:'',
                    healthRisk:'',
                    jobRisk:'',
                    livingAreaRisk:''
            }}
                validationSchema={Yup.object({
                    email: Yup.string()
                        .email('Invalid email address')
                        .required('Required'),
                    firstName: Yup.string()
                        .matches(/^[A-Za-z]+$/, 'Must only contain letters')
                        .max(30, 'Must be 30 characters or less')
                        .required('Required'),
                    lastName: Yup.string()
                        .matches(/^[A-Za-z]+$/, 'Must only contain letters')
                        .max(30, 'Must be 30 characters or less')
                        .required('Required'),
                    gender: Yup.string()
                        .oneOf(
                            ['MALE', 'FEMALE'],
                            'Invalid gender'
                        )
                        .required('Required'),
                    birthDate: Yup.string()
                        .required('Required'),
                    phoneNumber: Yup.string()
                        .matches(/^\+\d+$/, "Invalid phone number format")
                        .required('Required'),
                    country: Yup.string()
                        .max(15, 'Must be less than 15 characters')
                        .required('Required'),
                    state: Yup.string()
                        .max(15, 'Must be less than 15 characters')
                        .required('Required'),
                    city: Yup.string()
                        .max(15, 'Must be less than 15 characters')
                        .required('Required'),
                    postalCode: Yup.number()
                        .required('Required'),
                    address: Yup.string()
                        .max(30, 'Must be less than 30 characters')
                        .required('Required'),
                    ageRisk: Yup.string()
                        .oneOf(
                            ['LOW', 'MEDIUM','HIGH'],
                            'Invalid Value'
                        )
                        .required('Required'),
                    healthRisk: Yup.string()
                        .oneOf(
                            ['LOW', 'MEDIUM','HIGH'],
                            'Invalid Value'
                        )
                        .required('Required'),
                    jobRisk: Yup.string()
                        .oneOf(
                            ['LOW', 'MEDIUM','HIGH'],
                            'Invalid Value'
                        )
                        .required('Required'),
                    livingAreaRisk: Yup.string()
                        .oneOf(
                            ['LOW', 'MEDIUM','HIGH'],
                            'Invalid Value'
                        )
                        .required('Required'),
                })}
                onSubmit={(client, {setSubmitting}) => {
                    setSubmitting(true);
                    console.log(client)
                    saveInsuranceClient(client)
                        .then(res => {
                            console.log(res);
                            successNotification(
                                "Client saved",
                                `${client.firstName} was successfully saved`
                            )
                        }).catch(err => {
                            console.log(err);
                            errorNotification(
                                err.code,
                                err.response.data.message
                            )
                        }).finally(() => {
                            setSubmitting(false);
                        })
                }}
            >
                {({values,setFieldValue,isValid, isSubmitting}) => (
                        <>
                            <Form>
                                <Tabs>
                                    <TabList>
                                        <Tab>Personal Infos</Tab>
                                        <Tab>Address</Tab>
                                        <Tab>Risks</Tab>
                                    </TabList>

                                    <TabPanels>
                                        <TabPanel>
                                            <Stack spacing={"24px"}>
                                                <MyTextInput
                                                    label="Email"
                                                    name="email"
                                                    type="email"
                                                    placeholder="john_doe@mail.com"
                                                />
                                                <MyTextInput
                                                    label="First Name"
                                                    name="firstName"
                                                    type="text"
                                                    placeholder="John"
                                                />
                                                <MyTextInput
                                                    label="LastName"
                                                    name="lastName"
                                                    type="text"
                                                    placeholder="Doe"
                                                />
                                                <MySelect label="Gender" name="gender">
                                                    <option value="">Select gender</option>
                                                    <option value="MALE">Male</option>
                                                    <option value="FEMALE">Female</option>
                                                </MySelect>
                                                <MyDatePicker
                                                    label={"Birth Date"}
                                                    name={"birthDate"}
                                                    type={"date"}
                                                />
                                                <MyPhoneInput
                                                    label={"Phone Number"}
                                                    name={"phoneNumber"}
                                                    placeholder={"Enter Phone Number"}
                                                />
                                            </Stack>
                                        </TabPanel>

                                        <TabPanel>
                                            <Stack spacing={"24px"}>
                                                <MyAddressInput
                                                    setFieldValue={setFieldValue}
                                                    label="Address"
                                                    name="address"
                                                />

                                                <MyTextInput
                                                    label="Postal Code"
                                                    name="postalCode"
                                                    type="text"
                                                    placeholder="Enter Postal Code"
                                                />
                                                <MyTextInput
                                                    label="City"
                                                    name="city"
                                                    type="text"
                                                    placeholder="Enter City"
                                                />
                                                <MyTextInput
                                                    label="State"
                                                    name="state"
                                                    type="text"
                                                    placeholder="Enter State"
                                                />

                                                <MyTextInput
                                                    label="Country"
                                                    name="country"
                                                    type="text"
                                                    placeholder="Enter Country"
                                                />

                                            </Stack>
                                        </TabPanel>
                                        <TabPanel>
                                            <Stack spacing={"24px"}>
                                                <MySelect label="Age Risk" name="ageRisk">
                                                    <option value="">Select Risk</option>
                                                    <option value="LOW">LOW</option>
                                                    <option value="MEDIUM">MEDIUM</option>
                                                    <option value="HIGH">HIGH</option>
                                                </MySelect>

                                                <MySelect label="Health Risk" name="healthRisk">
                                                    <option value="">Select Risk</option>
                                                    <option value="LOW">LOW</option>
                                                    <option value="MEDIUM">MEDIUM</option>
                                                    <option value="HIGH">HIGH</option>
                                                </MySelect>
                                                <MySelect label="Job Risk" name="jobRisk">
                                                    <option value="">Select Risk</option>
                                                    <option value="LOW">LOW</option>
                                                    <option value="MEDIUM">MEDIUM</option>
                                                    <option value="HIGH">HIGH</option>
                                                </MySelect>
                                                <MySelect label="Living Area Risk" name="livingAreaRisk">
                                                    <option value="">Select Risk</option>
                                                    <option value="LOW">LOW</option>
                                                    <option value="MEDIUM">MEDIUM</option>
                                                    <option value="HIGH">HIGH</option>
                                                </MySelect>
                                            </Stack>
                                        </TabPanel>
                                    </TabPanels>
                                    <Button disabled={!isValid || isSubmitting} type="submit">Submit</Button>
                                </Tabs>
                            </Form>
                        </>
                    )
                }
            </Formik>
        </>
    )
}

export default CreateClientForm;