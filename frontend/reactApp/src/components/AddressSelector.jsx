import { useEffect, useRef, useState } from "react";
import './AddressSelector.css';

export default function AddressSelector({setFieldValue,initialAddress, initialCity, initialStateProvince, initialPostalCode, initialCountry}) {

    const locationRef = useRef();
    useEffect(() => {
        const SHORT_NAME_ADDRESS_COMPONENT_TYPES = new Set(['street_number', 'route', 'locality', 'postal_code']);
        const ADDRESS_COMPONENT_TYPES_IN_FORM = ['location', 'locality', 'administrative_area_level_1', 'postal_code', 'country'];

        const script = document.createElement('script');
        script.src = `https://maps.googleapis.com/maps/api/js?key=AIzaSyBbyXDsYCri39oj-hA5IAtR0iUtSY7Id9g&libraries=places,marker`;
        script.async = true;
        script.defer = true;
        script.addEventListener("load",initMap);
        document.body.appendChild(script);

        function initMap() {
            const { Autocomplete } = google.maps.places;

            const autocomplete = new Autocomplete(locationRef.current, {
                fields: ['address_components', 'geometry', 'name'],
                types: ['address'],
            });

            autocomplete.addListener('place_changed', () => {
                const place = autocomplete.getPlace();

                if (!place.geometry) {
                    window.alert(`No details available for input: '${place.name}'`);
                    return;
                }

                fillInAddress(place);
            });
        }

        function fillInAddress(place) {
            function getComponentName(componentType) {
                for (const component of place.address_components || []) {
                    if (component.types[0] === componentType) {
                        return SHORT_NAME_ADDRESS_COMPONENT_TYPES.has(componentType) ?
                            component.short_name :
                            component.long_name;
                    }
                }
                return '';
            }

            function getComponentText(componentType) {
                return (componentType === 'location') ?
                    `${getComponentName('street_number')} ${getComponentName('route')}` :
                    getComponentName(componentType);
            }

            for (const componentType of ADDRESS_COMPONENT_TYPES_IN_FORM) {
                if(componentType === 'location') setFieldValue('address', getComponentText('street_number') + ' ' + getComponentText('route'));
                else if(componentType === 'locality') setFieldValue('city', getComponentText('locality'));
                else if(componentType === 'administrative_area_level_1') setFieldValue('state', getComponentText('administrative_area_level_1'));
                else if(componentType === 'postal_code') setFieldValue('postalCode', getComponentText('postal_code'));
                else if(componentType === 'country') setFieldValue('country', getComponentText('country'));
            }
        }
    }, [setFieldValue]);


    return(
        <div className="card-container">
            <div className="panel">
                <input
                    type="text"
                    ref={locationRef}
                    placeholder="Address"
                    value={initialAddress}
                    // onChange={event => setFieldValue('address', event.target.value)} // Remove
                />
                <input
                    type="text"
                    placeholder="City"
                    value={initialCity}
                    // onChange={event => setFieldValue('city', event.target.value)} // Remove
                />
                <div className="half-input-container">
                    <input
                        type="text"
                        className="half-input"
                        placeholder="State/Province"
                        value={initialStateProvince}
                        // onChange={event => setFieldValue('state', event.target.value)} // Remove
                    />
                    <input
                        type="text"
                        className="half-input"
                        placeholder="Postal code"
                        value={initialPostalCode}
                        // onChange={event => setFieldValue('postalCode', event.target.value)}  // Remove
                    />
                </div>
                <input
                    type="text"
                    placeholder="Country"
                    value={initialCountry}
                    // onChange={event => setFieldValue('country', event.target.value)} // Remove
                />
            </div>
        </div>
    );
}